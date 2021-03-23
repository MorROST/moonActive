package test;

import com.mor.moona.api.Joke;
import com.mor.moona.api.JokesStore;
import com.mor.moona.api.Utility;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class Main {
    JokesStore jokesStore = new JokesStore();
    Utility utility = new Utility();

    //Test #1:
    @Test
    public void missingJoke(){
        assertTrue(jokesStore.getMissingJoke("test"));
    }

    //Test #2:
    @Test
    public void randomJoke() {
        int categoryNumPicked = 4;
        List<Joke> categoryMenu = jokesStore.getJokesOptionList("categories");
        if(categoryMenu.size() < categoryNumPicked) {
            System.out.println("Selection overflow\nNumber of Jokes categories: " + categoryMenu.size() + 1 + "your selection is: " + categoryNumPicked);
            throw new OutOfBounds();
        }
            String categorySelected = categoryMenu.get(categoryNumPicked - 1).getName();
            assertTrue(utility.writeJokeToFile(categorySelected));


    }

    public static class OutOfBounds extends RuntimeException{}
}
