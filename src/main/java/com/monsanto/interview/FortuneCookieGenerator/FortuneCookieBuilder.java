package com.monsanto.interview.FortuneCookieGenerator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FortuneCookieBuilder {

    private static final String NOT_DEFINED = "N/A";

    private String client = NOT_DEFINED;
    private String company = NOT_DEFINED;
    private String quote = NOT_DEFINED;

    FortuneCookieBuilder withClient(String client) {
       // if (this.client.equals(NOT_DEFINED)) -- Line removed of every setter;
            this.client = client;
        return this;
    }

    FortuneCookieBuilder withCompany(String company) {
        this.company = company;
        return this;
    }

    FortuneCookieBuilder withQuote(String quote) {
        this.quote = quote;
        return this;
    }

    FortuneCookie build() {
        return new FortuneCookie("Hi "+client+"! Thanks for buying at "+company+" :) And remember: "+quote);
    }

}
