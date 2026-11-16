package com.exemplo.notas;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/principal")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.exemplo.notas.steps")
public class RunCucumberTest {
}
