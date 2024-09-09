import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {
    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String topic = "test/topic";

        try {
            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
            client.connect();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter messages to send (type 'EOT' to exit):");
            String input;
            while (!(input = scanner.nextLine()).equals("EOT")) {
                String message = input + " (sent at " + getCurrentTimestamp() + ")";
                MqttMessage mqttMessage = new MqttMessage(message.getBytes());
                client.publish(topic, mqttMessage);
            }

            client.disconnect();
            System.out.println("Publisher shut off");
            scanner.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}