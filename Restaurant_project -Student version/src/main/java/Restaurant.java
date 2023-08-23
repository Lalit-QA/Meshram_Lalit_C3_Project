import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//New
public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;

    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE - correct above code
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE - corected above code
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }
//////////////////Part 2: Implementing Methods and Tests////
    public int calculateOrderValue(String... itemNames) {
        int totalOrderValue = 0;
        for (String itemName : itemNames) {
            Item item = findItemByName(itemName);
            if (item != null) {
                totalOrderValue += item.getPrice();
            }
        }
        return totalOrderValue;

    }
/////////////////////////////////////////////////

    public String getName() {
        return name;
    }

}
