import network
import time

def connect_to_wifi(ssid, password):
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(ssid, password)

    while not wlan.isconnected():
        print("Connecting to network...")
        time.sleep(1)

    print("Connected to WiFi, IP:", wlan.ifconfig())

# Replace 'your_SSID' and 'your_password' with your network details
connect_to_wifi('HamptonA', 'Stroud2Dell')
