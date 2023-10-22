package athleticli.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NutrientVerifierTest {

    @Test
    void verify_inputApprovedNutrients_expectTrue() {
        assertTrue(NutrientVerifier.verify("fats"));
    }
    @Test
    void verify_inputUnapprovedNutrients_expectFalse() {
        assertFalse(NutrientVerifier.verify("Vitamin A"));
    }
}
