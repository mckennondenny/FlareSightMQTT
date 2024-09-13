from umqtt.simple import MQTTClient

broker = "192.168.1.134"  # Replace with your desktop's IP
client_id = "pico_subscriber"
topic = b"test/topic"

# Callback function to handle incoming messages
def sub_callback(topic, msg):
    print(f"Received message: {msg} on topic: {topic}")

client = MQTTClient(client_id, broker)
client.set_callback(sub_callback)
client.connect()
client.subscribe(topic)

try:
    print(f"Subscribed to topic: {topic}")
    while True:
        # Wait for messages
        client.wait_msg()

finally:
    client.disconnect()
    print("Disconnected from broker")