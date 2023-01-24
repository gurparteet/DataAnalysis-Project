package utilitiesModule;

public class Country {
    private String countryCode;
    private String countryName;
    private String countryNameAbbr;

    private String countryComments;
    private String iso2digit;
    private String iso3digit;
    private String startYear;
    private String endYear;
    //im going to put the country csv to json to a list of this object


    public Country() {
    }
    public Country(String countryCode, String countryName, String countryNameAbbr,String countryComments,String iso2digit, String iso3digit, String startYear, String endYear) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countryNameAbbr = countryNameAbbr;
        this.countryComments = countryComments;
        this.iso2digit = iso2digit;
        this.iso3digit = iso3digit;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getIso2digit() {
        return iso2digit;
    }

    public void setIso2digit(String iso2digit) {
        this.iso2digit = iso2digit;
    }

    public String getIso3digit() {
        return iso3digit;
    }

    public void setIso3digit(String iso3digit) {
        this.iso3digit = iso3digit;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getCountryNameAbbr() {
        return countryNameAbbr;
    }

    public void setCountryNameAbbr(String countryNameAbbr) {
        this.countryNameAbbr = countryNameAbbr;
    }

    public String getCountryComments() {
        return countryComments;
    }

    public void setCountryComments(String countryComments) {
        this.countryComments = countryComments;
    }

}
