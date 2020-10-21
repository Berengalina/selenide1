package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Белоусова Анна");
        form.$("[data-test-id=phone] input").setValue("+79266858100");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitName() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Belousova Anna");
        form.$("[data-test-id=phone] input").setValue("+79266858100");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldNotSubmitPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Белоусова Анна");
        form.$("[data-test-id=phone] input").setValue("79266858100");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").shouldHave(exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldNotSubmitAgreement() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Белоусова Анна");
        form.$("[data-test-id=phone] input").setValue("+79266858100");
        form.$("button").click();
        $(".input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldNotFillName() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79266858100");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotFillPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form");
        form.$("[data-test-id=name] input").setValue("Белоусова Анна");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $(".input_invalid").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
    }

}