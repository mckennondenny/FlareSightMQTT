import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber {
    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String topic = "test/topic";

        try {
            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message received: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used in subscriber, still needs to be overwritten for Interface
                }
            });
            client.connect();
            client.subscribe(topic);
            System.out.println("Subscriber connected");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}