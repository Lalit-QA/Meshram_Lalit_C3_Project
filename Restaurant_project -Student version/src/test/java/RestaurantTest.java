import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //assertTrue(restaurant.isRestaurantOpen());
//        Restaurant mockedRestaurant = mock(Restaurant.class);
//        // Mock the getCurrentTime method to return a specific time between opening and closing time
//        when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("12:00:00"));
//
//        assertTrue(mockedRestaurant.isRestaurantOpen());
        // Set the current time to a time between opening and closing time
        LocalTime currentTime = LocalTime.parse("15:00:00");

        // Mock the getCurrentTime method to return the set current time
        restaurant = spy(restaurant);  // Using spy to partially mock the instance
        doReturn(currentTime).when(restaurant).getCurrentTime();

        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

      // assertFalse(restaurant.isRestaurantOpen());
        Restaurant mockedRestaurant = mock(Restaurant.class);
        // Mock the getCurrentTime method to return a time outside opening and closing time
        when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("14:00:00"));

        assertFalse(mockedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Part 3: Adding a Feature using TDD
    @Test
    public void calculate_order_value_for_items_in_menu() {
        int orderValue = restaurant.calculateOrderValue("Sweet corn soup", "Vegetable lasagne");
        assertEquals(388, orderValue);
    }

    @Test
    public void calculate_order_value_for_empty_item_list() {
        int orderValue = restaurant.calculateOrderValue();
        assertEquals(0, orderValue);
    }

    //Part 2 - fail test case
    @Test
    public void calculate_order_value_for_non_existing_item_should_get_fail() {
        int orderValue = restaurant.calculateOrderValue("Non-existing item");
        assertEquals(10, orderValue); // This is the expected order value, which is different from 0
    }
    //Part 3: Solution
    @Test
    public void calculate_order_value_for_non_existing_item_should_pass() {
        int orderValue = restaurant.calculateOrderValue("Non-existing item");
        assertEquals(0, orderValue); // Expecting the order value to be 0 for a non-existing item
    }


}

