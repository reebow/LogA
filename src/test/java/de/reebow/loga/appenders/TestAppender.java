package de.reebow.loga.appenders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "TestAppender", category = Core.CATEGORY_NAME,
  elementType = Appender.ELEMENT_TYPE, printObject = true)
public class TestAppender extends AbstractAppender {

  private List<LogEvent> messages = new ArrayList<>();

  protected TestAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
    super(name, filter, layout);
  }

  @Override
  public void append(LogEvent event) {
    getLogEvents().add(event);
  }


  @PluginFactory
  public static TestAppender createAppender(@PluginAttribute("name") String name,
    @PluginElement("Layout") Layout<? extends Serializable> layout,
    @PluginElement("Filter") final Filter filter,
    @PluginAttribute("otherAttribute") String otherAttribute) {
    if (name == null) {
      LOGGER.error("No name provided for TestAppender");
      return null;
    }
    if (layout == null) {
      layout = PatternLayout.createDefaultLayout();
    }
    return new TestAppender(name, filter, layout);
  }

  public List<LogEvent> getLogEvents() {
    return messages;
  }

}