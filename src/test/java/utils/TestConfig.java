package utils;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testData.properties"
})
public interface TestConfig extends Config {
    @Key("page")
    String testPage();
    String email();
    String password();
    String name();
    String lastname();
    String nameLatin();
    String lastnameLatin();
    String nameBlog();
    String birthday();
    String country();
    String city();
    String english();
    String contactType1();
    String contactType2();
    String contactData1();
    String contactData2();

}
