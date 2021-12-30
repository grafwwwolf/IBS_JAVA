


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "address",
        "phoneNumber",
        "inn",
        "founded",
        "securities"
})
@Generated("jsonschema2pojo")
public class Company {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("inn")
    private String inn;
    @JsonProperty("founded")
    private String founded;
    @JsonProperty("securities")
    private List<Security> securities = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("inn")
    public String getInn() {
        return inn;
    }

    @JsonProperty("inn")
    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty("founded")
    public String getFounded() {
        return founded;
    }

    @JsonProperty("founded")
    public void setFounded(String founded) {
        this.founded = founded;
    }

    @JsonProperty("securities")
    public List<Security> getSecurities() {
        return securities;
    }

    @JsonProperty("securities")
    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return name;
    }

    public void showSecurities() {
        AtomicInteger count = new AtomicInteger();
        System.out.println("----------------------------------");
        System.out.println("Держатель истекших акций: " + getName());
        this.securities.stream()
                .filter(security -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate date = LocalDate.parse(security.getDate(), formatter);
                    LocalDate currentDate = LocalDate.now();
                    return currentDate.isAfter(date);
                })
                .forEach(security -> {
                    System.out.println("Код: " + security.getCode() + ", Дата истечения: " + security.getDate() + ", эмитент: " + security.getName());
                    count.getAndIncrement();
                });
        System.out.println("Всего истекших акций: " + count);
        System.out.println("----------------------------------");
    }

    public void showSecuritiesByCurrency(String currencyCode) {
        boolean isHaveCurrency = false;
        for (Security sec : securities) {
            if (sec.getCurrency().contains(currencyCode)) {
                isHaveCurrency = true;
                break;
            }
        }
        if (isHaveCurrency) {
            System.out.println("Id :" + id + ", ");
            this.securities.stream()
                    .filter(security -> security.getCurrency().contains(currencyCode))
                    .forEach(security -> {
                        security.showSecuritiesByCurrency(currencyCode);
                    });
        }
    }
}
