package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:7777");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Белоусова Анна");
        form.$("[data-test-id=phone] input").setValue("+79266858100");
        form.$("[data-test-id=agreement]").click();
        form.$("[data-test-id=submit]").click();
        $(".alert-success").shouldHave(exactText("Ваша заявка успешно отправлена!"));
    }
}