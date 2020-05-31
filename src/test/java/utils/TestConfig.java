package utils;

import org.aeonbits.owner.Config;

public interface TestConfig extends Config {
    @Key("url")
    String testPage();
}
