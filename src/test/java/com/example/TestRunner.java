<<<<<<< HEAD
package com.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.stepdefinitions",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
=======
package com.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.stepdefinitions",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
>>>>>>> 85d468a (Upload project files with src and test folders)
}