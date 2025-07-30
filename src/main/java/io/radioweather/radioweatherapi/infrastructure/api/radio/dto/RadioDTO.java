package io.radioweather.radioweatherapi.infrastructure.api.radio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RadioDTO {

    @JsonProperty("changeuuid")
    private String changeUuid;

    @JsonProperty("stationuuid")
    private String stationUuid;

    @JsonProperty("serveruuid")
    private String serverUuid;

    private String name;
    private String url;

    @JsonProperty("url_resolved")
    private String urlResolved;

    private String homepage;
    private String favicon;
    private String tags;
    private String country;

    @JsonProperty("countrycode")
    private String countryCode;

    @JsonProperty("iso_3166_2")
    private String iso3166_2;

    private String state;
    private String language;

    @JsonProperty("languagecodes")
    private String languageCodes;

    private int votes;

    @JsonProperty("lastchangetime")
    private String lastChangeTime;

    @JsonProperty("lastchangetime_iso8601")
    private String lastChangeTimeIso8601;

    private String codec;
    private int bitrate;
    private boolean hls;

    @JsonProperty("lastcheckok")
    private boolean lastCheckOk;

    @JsonProperty("lastchecktime")
    private String lastCheckTime;

    @JsonProperty("lastchecktime_iso8601")
    private String lastCheckTimeIso8601;

    @JsonProperty("lastcheckoktime")
    private String lastCheckOkTime;

    @JsonProperty("lastcheckoktime_iso8601")
    private String lastCheckOkTimeIso8601;

    @JsonProperty("lastlocalchecktime")
    private String lastLocalCheckTime;

    @JsonProperty("lastlocalchecktime_iso8601")
    private String lastLocalCheckTimeIso8601;

    @JsonProperty("clicktimestamp")
    private String clickTimestamp;

    @JsonProperty("clicktimestamp_iso8601")
    private String clickTimestampIso8601;

    @JsonProperty("clickcount")
    private int clickCount;

    @JsonProperty("clicktrend")
    private int clickTrend;

    @JsonProperty("ssl_error")
    private int sslError;

    @JsonProperty("geo_lat")
    private double geoLat;

    @JsonProperty("geo_long")
    private double geoLong;

    @JsonProperty("geo_distance")
    private Double geoDistance;

    @JsonProperty("has_extended_info")
    private boolean hasExtendedInfo;
}
