package org.springframework.data.cassandra.config.xml;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.data.cassandra.config.DefaultDataBeanNames;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Spring Data Cassandra XML namespace parser for the &lt;converter&gt; element.
 * 
 * @author Matthew T. Adams
 */
public class CassandraMappingConverterParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return MappingCassandraConverter.class;
	}

	@Override
	protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext)
			throws BeanDefinitionStoreException {

		String id = super.resolveId(element, definition, parserContext);
		return StringUtils.hasText(id) ? id : DefaultDataBeanNames.CONVERTER;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {

		String mappingRef = element.getAttribute("mapping-ref");
		if (!StringUtils.hasText(mappingRef)) {
			mappingRef = DefaultDataBeanNames.MAPPING_CONTEXT;
		}

		builder.addConstructorArgReference(mappingRef);
	}
}
