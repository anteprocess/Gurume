package com.anteprocess.food.datapack

import com.anteprocess.food.data.util.NetworkResult
import com.anteprocess.food.models.FoodJoke

class PremadeFoodJoke {
    companion object {
        var foodJokeList = arrayListOf<String>()
        fun getFoodJokePack(): ArrayList<String> {
            foodJokeList.add(
                "What do you call a fake noodle?\n" +
                        "\n" +
                        "An impasta."
            )
            foodJokeList.add(
                "What did the baby corn say to its mom?\n" +
                        "\n" +
                        "Where’s my popcorn?"
            )
            foodJokeList.add(
                "Why couldn’t the sesame seed leave the gambling casino?\n" +
                        "\n" +
                        "Because he was on a roll."
            )
            foodJokeList.add("What does a nosey pepper do?\n" +
                    "\n" +
                    "Gets jalapeño business.")

            foodJokeList.add("What’s orange and sounds like a parrot?\n" +
                    "\n" +
                    "A carrot.")

            foodJokeList.add("When potatoes have babies, what are they called?\n" +
                    "\n" +
                    "Tater tots.")

            foodJokeList.add("How fast is milk?\n" +
                    "\n" +
                    "It’s pasteurized before you know it.")

            foodJokeList.add("How do you make an apple turnover?\n" +
                    "\n" +
                    "Push it downhill.\n" +
                    "\n")
            foodJokeList.add("What kind of socks do you need to plant asparagus?\n" +
                    "\n" +
                    "Garden hose.")

            foodJokeList.add("Why did the skeleton go to the barbecue?\n" +
                    "\n" +
                    "To get another rib.")

            foodJokeList.add("What did the pecan say to the walnut?\n" +
                    "\n" +
                    "We’re friends because we’re both nuts.")

            foodJokeList.add("Where did the broccoli go to have a few drinks?\n" +
                    "\n" +
                    "The salad bar.")

            foodJokeList.add("What did one blueberry say to the other blueberry?\n" +
                    "\n" +
                    "If you weren’t so sweet, we wouldn’t be in this jam.")

            foodJokeList.add("What do you call cheese that is not ours?\n" +
                    "\n" +
                    "Nacho cheese.")

            foodJokeList.add("Wanna hear a joke about pizza?\n" +
                    "\n" +
                    "Never mind, it’s too cheesy.")

            foodJokeList.add("What’s the best way to burn vegetables?\n" +
                    "\n" +
                    "Roast them.")

            foodJokeList.add("Which condiment adds the most kick?\n" +
                    "\n" +
                    "Horseradish.")

            foodJokeList.add("Why are butchers so hilarious?\n" +
                    "\n" +
                    "They always ham it up.")

            foodJokeList.add("Which friends should you take to dinner?\n" +
                    "\n" +
                    "Your taste buds.")

            foodJokeList.add("What should you do if your soup is too hot?\n" +
                    "\n" +
                    "Add a chilly pepper.")

            foodJokeList.add("What part of a meal makes you the most sleepy?\n" +
                    "\n" +
                    "A nap-kin.")
            foodJokeList.add("What’s an omnivore’s favorite food?\n" +
                    "\n" +
                    "Zoo-chini.")
            foodJokeList.add("When is eating just like school?\n" +
                    "\n" +
                    "When you have three or four courses.")
            foodJokeList.add("Why did the butcher work extra hours at the shop?\n" +
                    "\n" +
                    "To make ends meat.")

            foodJokeList.add("What do you call blueberries playing the guitar?\n" +
                    "\n" +
                    "A jam session.")

            foodJokeList.add("What’s the most relaxing type of pasta?\n" +
                    "\n" +
                    "Spa-ghetti.\n" +
                    "\n")


            foodJokeList.add("How do you truly savor a hot dog?\n" +
                    "\n" +
                    "With relish.")

            foodJokeList.add("How do you ask a foodie out to dinner?\n" +
                    "\n" +
                    "“Lettuce meat for a date.”")

            foodJokeList.add("What’s the best thing to put into a pie?\n" +
                    "\n" +
                    "Your teeth!")

            foodJokeList.add("When do you go at red and stop at green?\n" +
                    "\n" +
                    "When you’re eating a watermelon.")

            foodJokeList.add("Did you hear the joke about the peanut butter?\n" +
                    "\n" +
                    "I’m not telling you. You might spread it!")

            foodJokeList.add("Why do the French like to eat snails?\n" +
                    "\n" +
                    "Because they don’t like fast food.")

            foodJokeList.add("What kind of nuts always seem to have a cold?\n" +
                    "\n" +
                    "Cashews!")

            foodJokeList.add("Why did the banana go to the doctor?\n" +
                    "\n" +
                    "I wasn’t peeling well.")

            foodJokeList.add("In which school do you learn how to make ice cream?\n" +
                    "\n" +
                    "Sunday School")

            foodJokeList.add("Why shouldn’t you tell a secret on a farm?\n" +
                    "\n" +
                    "Because the potatoes have eyes and the corn have ears.")

            foodJokeList.add("Did you hear about the carrot detective?\n" +
                    "\n" +
                    "He got to the root of every case.")

            foodJokeList.add("Why does yogurt love going to museums?\n" +
                    "\n" +
                    "Because it’s cultured.")

            foodJokeList.add("How do you measure the weight of crackers?\n" +
                    "\n" +
                    "In grahams.")

            foodJokeList.add("Boy, I just got hit in the head with a soda. I was lucky it was a soft drink. \n" +
                    "\n" +
                    "What do you give to a sick lemon?\n" +
                    "\n" +
                    "Lemon-aid.")

            foodJokeList.add("What are twins favorite fruit?\n" +
                    "\n" +
                    "Pears!")

            foodJokeList.add("Why do melons prefer weddings?\n" +
                    "\n" +
                    "They cantaloupe!")

            foodJokeList.add("What do you get when you put three ducks in a box?\n" +
                    "\n" +
                    "A box of quackers!")

            foodJokeList.add("What happened to the slices of bread that disappeared overnight?\n" +
                    "\n" +
                    "Turned out the e-loafed!")

            foodJokeList.add("What did the Ketchup say to the hot dog? \n" +
                    "\n" +
                    "Nice to meat you!")

            foodJokeList.add("Why couldn’t the hamburger stop making jokes?\n" +
                    "\n" +
                    "He was on a roll!")

            foodJokeList.add("Why didn’t the gardener get around to planting an herb garden?\n" +
                    "\n" +
                    "They couldn’t find the thyme!")

            foodJokeList.add("What’s the most desirable kitchen appliance?\n" +
                    "\n" +
                    "A “hot” plate.")

            foodJokeList.add("Why are chefs so harsh?\n" +
                    "\n" +
                    "They’re always beating eggs.")

            foodJokeList.add("What is the most attractive fruit?\n" +
                    "\n" +
                    "A fine-apple.")

            return foodJokeList
        }
    }
}