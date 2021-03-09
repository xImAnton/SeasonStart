# SeasonStart
a minecraft plugin for managing minecraft smp starts

## Installation
To use this plugin you got to have a [spigot](https://getbukkit.org/download/spigot) or [paper](https://papermcc.io) minecraft server. Build this or download a prebuilt jar and insert it into your plugins folder. Restart your server or type /rl confirm

## Usage
This plugin provides a single command: /start. You can use it to start the smp (Survival Multiplayer).

## Config
While `seasonStarted` in the config is false, users can, depending on your config, not move or take no damage. The /start command releases all players and let them take damage.

### Config Values

| Key                     | Description                                                  | Type                           | Default   |
| ----------------------- | ------------------------------------------------------------ | ------------------------------ | --------- |
| players.immovable       | When true, players can't move and break or place blocks when the season hasn't started. | boolean                        | true      |
| players.invulnerable    | When true, players can't take or deal damage when the season hasn't started. | boolean                        | true      |
| modifySpawnLocation     | Whether to teleport all players on first login to a specific location (`spawnLocation`). | boolean                        | false     |
| spawnLocation.x, .y, .z | The coordinates of the spawn location.                       | int                            | 0, 0, 0   |
| spawnLocation.dimension | The dimension of the spawn location.                         | String: overworld, nether, end | overworld |
| seasonStarted           | Whether the season has started or not. Gets set on /start command | boolean                        | false     |
| ignoreCreativePlayers   | Whether players in creative or spectator mode can move, even though `players.immovable` is true. | boolean                        | true      |
| startItems              | Items to give the player first join.                         | List[Material, int]            | APPLE: 1  |

