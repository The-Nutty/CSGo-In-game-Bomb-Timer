# CSGo-In-game-Bomb-Timer
This is a java made overlay for CSGO that displays the bomb timer.

To download either build in eclipse or download for releases(when ready).

Screen shot from in game: http://imgur.com/o7vxbly

I am taking ANY recomendations for the visual look as i know its not ideal ATM

How to install:

0.5. make sure java is installed

1. place the config (config/gamestate_intergration_CSGOTimer.cfg) in your csgo config directory(typicly %path to steam%\steamapps\common\Counter-Strike Global Offensive\csgo\cfg.

2. Run the JAR this should only show a java icon in the task bar at the min.

3. Put CSGO in windowed mode (note that this will lightly not work if you use CSGO at non native res (back bars might be fine))

3. get in game and plant the bomb :)

It genarates a Jframe thats the size of a 1080p monitor and cant be clicked and is trasparent. A JLable sits on top of it with the bomb timer(after the bomb is planted)
Thanks to https://github.com/LangdalP/GoTimer for the insperation.

FAQ:
Q:its not working A: open console in csgo dose it say "Loading Game State Integration: gamestate_integration_CSGOTimer.cfg" if not make sure you have placed the config in the corect place. otherwise check for a pop up you might have not seen.

Q: Is this not basicly a coustom hud? can this be used to make one. A: YES!!!1! yeah basicly this could be used by disableing ingame hud and using this method. However currently you cant get the state of other players without being a spectator. So a specator HUD is very posible. Not so much a regular one.

Q: Anything else contact me through github or on reddit /u/Nutty007