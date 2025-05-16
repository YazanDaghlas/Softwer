package YazanSystem;

import org.example.INPUT_TEST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class INPUT_TESTTest {

    private INPUT_TEST inputTest;

    @BeforeEach
    public void setUp() {
        inputTest = new INPUT_TEST();
    }

    @Test
    public void testCustomerLoginAndFetchOrderHistory() {
        inputTest.fetchOrderHistory(); // Should not load
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory(); // Should load Shawerma and Pizza
    }

    @Test
    public void testAdminLoginAndRequestCustomerOrderData() {
        inputTest.requestCustomerOrderData(); // Access denied
        inputTest.loginAdmin();
        inputTest.requestCustomerOrderData(); // Access granted
    }

    @Test
    public void testAccessStoredOrderHistory() {
        INPUT_TEST.accessStoredOrderHistory(); // Empty
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();
        INPUT_TEST.accessStoredOrderHistory(); // Loaded
    }

    @Test
    public void testSuggestPersonalizedMealPlan() {
        inputTest.suggestPersonalizedMealPlan(); // Always suggests
    }

    @Test
    public void testAnalyzeTrendsAndGenerateReports() {
        inputTest.analyzeTrendsAndGenerateReports(); // Always generates report
    }

    @Test
    public void testNavigateToCustomerProfile() {
        inputTest.navigateToCustomerProfile(); // Access denied
        inputTest.loginChef();
        inputTest.navigateToCustomerProfile(); // Access granted
    }

    @Test
    public void testSelectMealToReorder() {
        inputTest.selectMealToReorder(); // No orders
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();
        inputTest.selectMealToReorder(); // Shawerma
    }

    @Test
    public void testNavigateToPageAsCustomer() {
        inputTest.navigateToPage("Order History"); // Failed
        inputTest.loginCustomer();
        inputTest.navigateToPage("Order History"); // Success
    }

    @Test
    public void testNavigateToPageAsAdmin() {
        inputTest.navigateToPage("Admin Dashboard"); // Failed
        inputTest.loginAdmin();
        inputTest.navigateToPage("Admin Dashboard"); // Success
    }

    @Test
    public void testNavigateToPageAsChef() {
        inputTest.navigateToPage("Chef Dashboard"); // Failed
        inputTest.loginChef();
        inputTest.navigateToPage("Chef Dashboard"); // Success
    }


}
