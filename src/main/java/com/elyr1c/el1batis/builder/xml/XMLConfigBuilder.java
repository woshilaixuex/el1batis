package com.elyr1c.el1batis.builder.xml;

import com.elyr1c.el1batis.Configuration;
import com.elyr1c.el1batis.builder.BaseBuilder;
import com.elyr1c.el1batis.io.Resources;
import com.elyr1c.el1batis.mapping.MappedStatement;
import com.elyr1c.el1batis.mapping.SqlCommandType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName XMLConfigBuilder
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/26 23:07
 */
public class XMLConfigBuilder extends BaseBuilder {
    private Element configXMLRoot;
    public XMLConfigBuilder(Reader reader) {
        super(new Configuration());
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            configXMLRoot = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
    public Configuration parse(){
        try {
            mapperElement(configXMLRoot.element("mappers"));
        }catch (Exception e){
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }
    // The logic is load mapper config xml file and load config`s xml file.
    public void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element element: mapperList) {
            String resource = element.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element mapperXMLRoot = document.getRootElement();
            // SELECT
            String namespace = loadSelectXML(mapperXMLRoot);
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
    private String loadSelectXML(Element mapperXMLRoot) {
        String namespace = mapperXMLRoot.attributeValue("namespace");
        List<Element> selectNodes = mapperXMLRoot.elements("select");
        for (Element node: selectNodes) {
            String id = node.attributeValue("id");
            String parameterType = node.attributeValue("parameterType");
            String resultType = node.attributeValue("resultType");
            String sql = node.getText();

            Map<Integer, String> parameter = new HashMap<>();
            Pattern pattern = Pattern.compile("(#\\{(.*?)})");
            Matcher matcher = pattern.matcher(sql);
            for (int i = 1; matcher.find(); i++) {
                String g1 = matcher.group(1);
                String g2 = matcher.group(2);
                parameter.put(i, g2);
                sql = sql.replace(g1, "?");
            }

            String msId = namespace + "." + id;
            String nodeName = node.getName();
            SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
            MappedStatement mappedStatement = new MappedStatement.Builder(
                    configuration,
                    msId,
                    sqlCommandType,
                    parameterType,
                    resultType,
                    sql,
                    parameter).build();
            // 添加解析 SQL
            configuration.addMappedStatement(mappedStatement);
        }
        return namespace;
    }
}
