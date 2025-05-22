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
        inputTest.fetchOrderHistory();
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();
    }

    @Test
    public void testAdminLoginAndRequestCustomerOrderData() {
        inputTest.requestCustomerOrderData();
        inputTest.loginAdmin();
        inputTest.requestCustomerOrderData();
    }

    @Test
    public void testAccessStoredOrderHistory() {
        INPUT_TEST.accessStoredOrderHistory();
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();
        INPUT_TEST.accessStoredOrderHistory();
    }

    @Test
    public void testSuggestPersonalizedMealPlan() {
        inputTest.suggestPersonalizedMealPlan();
    }

    @Test
    public void testAnalyzeTrendsAndGenerateReports() {
        inputTest.analyzeTrendsAndGenerateReports();
    }

    @Test
    public void testNavigateToCustomerProfile() {
        inputTest.navigateToCustomerProfile();
        inputTest.loginChef();
        inputTest.navigateToCustomerProfile();
    }

    @Test
    public void testSelectMealToReorder() {
        inputTest.selectMealToReorder();
        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();
        inputTest.selectMealToReorder();
    }

    @Test
    public void testNavigateToPageAsCustomer() {
        inputTest.navigateToPage("Order History");
        inputTest.loginCustomer();
        inputTest.navigateToPage("Order History");
    }

    @Test
    public void testNavigateToPageAsAdmin() {
        inputTest.navigateToPage("Admin Dashboard");
        inputTest.loginAdmin();
        inputTest.navigateToPage("Admin Dashboard");
    }

    @Test
    public void testNavigateToPageAsChef() {
        inputTest.navigateToPage("Chef Dashboard");
        inputTest.loginChef();
        inputTest.navigateToPage("Chef Dashboard");
    }


}
