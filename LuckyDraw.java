import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Prize {
    private final int amount;
    private final String voucherId;
    private final String category;

    public Prize(int amount, String voucherId, String category) {
        this.amount = amount;
        this.voucherId = voucherId;
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public String getCategory() {
        return category;
    }
}

class Customer {
    private final int id;
    private final String name;
    private final String phoneNumber;
    private Prize prize;

    public Customer(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int customerId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }
}

public class LuckyDraw {
    private static final String[] RANDOM_NAMES = {
        "Alex", "Jordan", "Taylor", "Morgan", "Casey", "Blake", "Reese", "Peyton", "Quinn", "Riley", 
        "Drew", "Devon", "Logan", "Jamie", "Cameron", "Avery", "Charlie", "Skyler", "Emerson", "Finley"
    };

    public static void main(String[] args) {
        String[] categories = {"Clothes", "Electronics", "Food Items", "Baby Toys", "Cosmetics"};
        int[][] prices = {
            {25000, 26000, 27000, 28000, 30000},
            {36000, 37000, 38000, 39000, 40000},
            {56000, 57000, 58000, 59000, 60000},
            {66000, 67000, 68000, 69000, 70000},
            {96000, 97000, 98000, 99000, 100000}
        };

        List<Prize> prizes = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < prices[i].length; j++) {
                prizes.add(new Prize(prices[i][j], "VOUCHER" + (i * prices[i].length + j + 1), categories[i]));
            }
        }

        List<Customer> customers = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            String name = RANDOM_NAMES[random.nextInt(RANDOM_NAMES.length)];
            customers.add(new Customer(i, name, "987654321" + i % 10));
        }

        Collections.shuffle(customers);

        for (int i = 0; i < 25; i++) {
            customers.get(i).setPrize(prizes.get(i));
        }

        List<Customer> luckyWinners = customers.subList(0, 25);
        luckyWinners.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));

        System.out.println("Lucky winners and their prizes:");
        for (String category : categories) {
            printCategoryWinners(luckyWinners, category);
            System.out.println("--------------------------------------------");
        }
    }

    private static void printCategoryWinners(List<Customer> winners, String category) {
        System.out.println("Category: " + category);
        for (Customer customer : winners) {
            Prize prize = customer.getPrize();
            if (prize.getCategory().equals(category)) {
                System.out.printf("Customer ID: %d, Name: %s, Phone: %s, Voucher ID: %s, Prize: %d INR\n",
                    customer.getId(), customer.getName(), customer.getPhoneNumber(), prize.getVoucherId(), prize.getAmount());
            }
        }
    }
}
