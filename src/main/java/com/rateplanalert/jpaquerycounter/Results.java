package com.rateplanalert.jpaquerycounter;

import static com.rateplanalert.jpaquerycounter.OutputFile.*;

import java.util.LinkedHashMap;
import java.util.Map;

class Results {

    private static final Map<String, String> values = new LinkedHashMap<>();
    private static final OutputFile OUTPUT_FILE = new OutputFile();
    private static final Results INSTANCE = new Results();

    static Results getInstance() {
        return INSTANCE;
    }

    void addResult(String result) {
        String api = parseApi(result);
        if (isDuplicated(api)) {
            // 이전 값 갱신
            values.put(api, result);
            return;
        }
        values.put(api, result);
        // 새 값 들어오면 파일 내용 초기화하고 write
        OUTPUT_FILE.write(getString());
    }

    private String parseApi(final String result) {
        return result.split(LINE_SEPARATOR)[0];
    }

    boolean isDuplicated(String result) {
        return values.containsKey(parseApi(result));
    }

    private String getString() {
        return String.join("", values.values());
    }

    private Results() {
    }
}
