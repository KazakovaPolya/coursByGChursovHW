import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryTest {
    @Step("Check destinationDistant negative number")
    @Tag("Negative")
    @DisplayName("Check destinationDistant negative number")
    @Test
    void calculateTestNegativeDistance(){
        Delivery delivery = new Delivery(-1, true, true, "NORMAL");
        assertThrows(IllegalArgumentException.class, () -> delivery.calculateDeliveryCost(),
                "destinationDistance should be a positive number!");
    }

    @Step("Check exception 'Fragile cargo cannot be delivered for the distance more than 30'")
    @Tag("Negative")
    @DisplayName("Check exception 'Fragile cargo cannot be delivered for the distance more than 30'")
    @ParameterizedTest()
    @CsvSource({
            "101, true, true, VERY_HIGHT",
            "31, true, true, LOW_HIGHT"
    })
    void calculateTestException(int distance, boolean dimansions, boolean fragile, String deliveryRate){
        Delivery delivery = new Delivery(distance, dimansions, fragile, deliveryRate);
        assertThrows(UnsupportedOperationException.class, () -> delivery.calculateDeliveryCost(),
                "Fragile cargo cannot be delivered for the distance more than 30");
    }

    @Step("Check expected resul 400")
    @Tag("Positive")
    @DisplayName("Check expected resul 400")
    @ParameterizedTest()
    @CsvSource({
            "31, false, false, NORMAL",
            "9, false, false, NORMAL",
            "4, false, false, HIGHT",
            "1, true, false, LOW_HIGHT",
            "1, true, false, VERY_HIGHT"
    })
    void calculateTest400(int distance, boolean dimansions, boolean fragile, String deliveryRate){
        Delivery delivery = new Delivery(distance, dimansions, fragile, deliveryRate);
        Assertions.assertEquals(400.0, delivery.calculateDeliveryCost());
    }

    @Step("Check expected resul 600")
    @Tag("Positive")
    @DisplayName("Check expected resul 600")
    @Test
    void calculateTest600(){
        Delivery delivery = new Delivery(30, false, true, "NORMAL");
        Assertions.assertEquals(600.0, delivery.calculateDeliveryCost());
    }
}
