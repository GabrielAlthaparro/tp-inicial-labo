from kivymd.app import MDApp
from kivy.uix.screenmanager import ScreenManager
from kivy.core.text import LabelBase
from kivy.lang import Builder
from kivy.core.window import Window
Window.size= (350,580)

class Canchas(MDApp):
    def build(self):
        global screen_manager
        screen_manager=ScreenManager()
        screen_manager.add_widget(Builder.load_file("loginTouch.kv"))
        ##screen_manager.add_widget(Builder.load_file("chatBot.kv"))
        return screen_manager


Canchas().run()