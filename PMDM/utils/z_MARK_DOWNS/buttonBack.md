### 1\. Using Intent with Data

In this approach, you use an intent to navigate back to the previous activity and pass data along with it.

1.  In the Sending Activity:

```java

    Intent resultIntent = new Intent();
    resultIntent.putExtra("key", "value"); // Add data to the intent
    setResult(Activity.RESULT_OK, resultIntent);
    finish();
```

2.  In the Receiving Activity:

```java


    if (requestCode == YOUR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
        String receivedData = data.getStringExtra("key"); // Retrieve data from the intent
        // Process the data as needed
    }

```

### 2\. Using setResult with CANCEL

In this approach, you set the result to `Activity.RESULT_CANCELED` without passing an intent.

1.  In the Sending Activity:

```java

setResult(Activity.RESULT_CANCELED);
finish();
```

2.  In the Receiving Activity:

```java

@Override

    if (resultCode == Activity.RESULT_CANCELED) {
        // Handle the cancellation event
    }

```
