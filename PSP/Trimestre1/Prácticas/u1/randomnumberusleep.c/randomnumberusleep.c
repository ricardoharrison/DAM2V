#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>  // Include the library for sleep

int main() {
    // Seed the random number generator with the current time
    srand(time(NULL));

    // Generate and print a random integer between 1 and 7 with a half-second delay
    for (int i = 0; i < 15; i++) {
        int random_number = rand() % 7 + 1;  // % 7 limits the range to 0-6, and then add 1
        printf("Random Number: %d\n", random_number);

        // Introduce a half-second delay
        usleep(500000);  // 500,000 microseconds = 0.5 seconds
    }

    return 0;
}