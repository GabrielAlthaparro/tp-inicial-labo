from kivymd.app import MDApp
from kivy.uix.screenmanager import ScreenManager
from kivy.core.text import LabelBase
from kivy.lang import Builder
from kivy.core.window import Window
from kivy.app import App
from pyfingerprint.pyfingerprint import PyFingerprint
Window.size= (350,580)

class Canchas(MDApp):
    def build(self):


        global screen_manager
        screen_manager=ScreenManager()
        screen_manager.add_widget(Builder.load_file("loginTouch.kv"))
        ##screen_manager.add_widget(Builder.load_file("chatBot.kv"))

        return screen_manager
def authenticate_fingerprint(self):
        try:
            fingerprint = PyFingerprint('/dev/ttyUSB0', 57600, 0xFFFFFFFF, 0x00000000)
            if not fingerprint.verifyPassword():
                raise ValueError('Password incorrecta')
            print("Sensor de huella digital inicializado correctamente.")
            # Lógica para autenticar la huella digital aquí
        except Exception as e:
            print('Error:', e)
            # Manejar errores de inicialización del sensor de huellas digitales
            # Puedes mostrar un mensaje de error en la interfaz de usuario si lo deseas
            self.show_error_message("Error de autenticación de huella digital")


LabelBase.register(name="BMont",fn_regular="assets\\static\\Montserrat-Bold.ttf")
Canchas().run()