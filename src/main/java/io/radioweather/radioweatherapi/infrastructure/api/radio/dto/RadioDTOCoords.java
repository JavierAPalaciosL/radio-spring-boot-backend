package io.radioweather.radioweatherapi.infrastructure.api.radio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RadioDTOCoords {
    @JsonProperty("changeuuid")
    private String changeUuid;

    @JsonProperty("stationuuid")
    private String stationUuid;

    @JsonProperty("serveruuid")
    private String serverUuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("url_resolved")
    private String urlResolved;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("favicon")
    private String favicon;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("country")
    private String country;

    @JsonProperty("countrycode")
    private String countryCode;

    @JsonProperty("iso3166_2")
    private String iso3166_2;

    @JsonProperty("state")
    private String state;

    @JsonProperty("language")
    private String language;

    @JsonProperty("language_codes")
    private String languageCodes;

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("lastchangetime")
    private String lastChangeTime;

    @JsonProperty("lastchangetime_iso8601")
    private String lastChangeTimeIso8601;

    @JsonProperty("codec")
    private String codec;

    @JsonProperty("bitrate")
    private int bitrate;

    @JsonProperty("hls")
    private int hls;

    @JsonProperty("lastcheckok")
    private int lastCheckOk;

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

    @JsonProperty("sslerror")
    private int sslError;

    @JsonProperty("geo_lat")
    private double geoLat;

    @JsonProperty("geo_long")
    private double geoLong;

    @JsonProperty("geo_distance")
    private double geoDistance;

    @JsonProperty("has_extended_info")
    private boolean hasExtendedInfo;
}
