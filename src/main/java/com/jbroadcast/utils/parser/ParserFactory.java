package com.jbroadcast.utils.parser;

public class ParserFactory {
    private static ArgsParser argsParser = null;

    public static ArgsParser getArgsParser() {
        if (argsParser == null) {
            throw new RuntimeException("Args Parser is Null");
        }
        return argsParser;
    }

    public static void setArgsParser(final ArgsParser anArgsParser) {
        argsParser = anArgsParser;
    }
}
