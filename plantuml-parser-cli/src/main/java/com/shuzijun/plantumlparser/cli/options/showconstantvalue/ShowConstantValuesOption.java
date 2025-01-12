package com.shuzijun.plantumlparser.cli.options.showconstantvalue;

import com.shuzijun.plantumlparser.cli.options.CliOption;
import com.shuzijun.plantumlparser.core.ParserConfig;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.Arrays;

public class ShowConstantValuesOption implements CliOption {

    protected final String optName = "scval";
    protected final String longOptName = "show_constant_val";
    protected final Option option;

    public ShowConstantValuesOption() {
        this.option = new Option(this.optName,  this.longOptName, false, "Show constant values");
    }

    @Override
    public ParserConfig updateConfig(final ParserConfig parserConfig, final CommandLine cmd) {
        Arrays.stream(cmd.getOptions()).map(Option::getOpt)
                .filter(option -> option.equalsIgnoreCase(this.optName))
                .findAny()
                .ifPresent(a -> parserConfig.setShowConstantValues(true));
        return parserConfig;
    }

    @Override
    public Option getOption() {
        return this.option;
    }
}
