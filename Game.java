/*ZONK Adventure Game: 
 * @Author Janna Gilleman, Ryan Emerson, Chelsea Fowler
 * @Date Fall 22
 * Class Game runs the main game
*/
import java.util.Scanner;

public class Game {
    
    public static void main(String[] args) {
        Train train = new Train();
        Player player = new Player("LAIKA",2, train);

        //(uncomment this stuff when we have a functioning gameloop)
        boolean stillPlaying = true;
        Scanner userInput = new Scanner(System.in);
        String userResponse = "";

        //populate cars with passengers
        train.cars[0].addPassenger("LINDA", "long flowy red hair and a few freckles, and is looking absently out the window.", "Hello young man; you look confused. Haha, aren't we all. Why don't you go talk to my son Anton. He's about your age.");
        train.cars[0].addPassenger("WILLIAM", "courdouroy pants, a cotton t-shirt, and is smoking a pipe.", "My great grandmother told me of a beast that lurks these tracks. You don't believe in such things though, do you?");
        train.cars[0].addPassenger("ANTON", "a cozy hoodie with headphones and is reading a book.", "I saw you stole that keycard. Whatever you're up to, I get half, okay?");

        //load items into the cars
        train.cars[0].addItem("KEYCARD", "A thin plastic card. The only parts you can read are \"Lionel...130 Peice");

        //load the player in with a pair of glasses
        player.givePlayer("glasses", "Large round spectacles. Carved on the inside are the letters \"LAIKA\".\n" +
        "You don't remember much about who you are or how you got here, but your fondness of these glasses is evident from their wear.");

        //Game Opening Text
        System.out.println("\n<<<<o>  Welcome to ZONK  <o>>>>\n");
        System.out.println("You wake up on a train, unaware of where you are or how you got there." + 
        " In your haze, you see a frog in a conductor's outfit scan a keycard up to the train car exit door." +
        " As he exits the car, the keycard falls under a seat. You feel you should start by exploring the carriage you're in and talking to the passengers.\n");
        System.out.println(">> ENTER COMMANDS BELOW: ");

        //Main Game Loop
        while (stillPlaying == true) {
            userResponse = userInput.nextLine().toUpperCase();
            if (userResponse.contains("EXIT GAME") || userResponse.contains("END GAME") || userResponse.contains("QUIT") || userResponse.contains("LOOK AROUND") || userResponse.contains("TALK TO") || userResponse.contains("PICK UP") || userResponse.contains("NEXTCAR") || userResponse.contains("NEXT CAR") || userResponse.contains("FORWARD") ||  userResponse.contains("PREVIOUSCAR") || userResponse.contains("PREVIOUS CAR") || userResponse.contains("BACKWARD")){
                if (userResponse.contains("EXIT GAME") || userResponse.contains("END GAME") || userResponse.contains("QUIT")) {
                    System.out.println("You've ended your game of ZONK, I hope you travel with us again!");
                    stillPlaying = false;
                }
                if (userResponse.contains("LOOK AROUND")) {
                    player.lookAround();
                }
                if (userResponse.contains("TALK TO")) {
                    String name = userResponse.split(" ")[2]; // split user response at every space and take the second word. this should be all uppercase
                    if (!train.cars[player.location].carPassengers.containsKey(name)) { // if name not in carPassengers, print error
                        System.out.println("Please enter valid passenger.");
                    } else {
                        player.talkTo(name);
                    }
                }
                if (userResponse.contains("PICK UP")) {
                    String name = userResponse.split(" ")[2];
                    if (!train.cars[player.location].carItems.containsKey(name)) {
                        System.out.println("Please enter valid item name.");
                    } else {
                        player.pickUp(name);
                    }
                }
                if (userResponse.contains("NEXTCAR") || userResponse.contains("NEXT CAR") || userResponse.contains("FORWARD")) {
                  if (player.holding("KEYCARD")){
                    player.nextCar();
                  }
                  else {
                    System.out.println("You don't have the credentials to move to the next car");
                  }
                }
                if (userResponse.contains("PREVIOUSCAR") || userResponse.contains("PREVIOUS CAR") || userResponse.contains("BACKWARD")) {
                  if (player.holding("KEYCARD")){
                    player.previousCar();
                  }
                  else {
                    System.out.println("You don't have the credentials to move to the next car");
                  }
                }
            }
        }
        //Close out game
        userInput.close();
    }
}