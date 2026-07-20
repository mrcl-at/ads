# Ads

A Paper based plugin that lets players buy ad broadcast in the chat.

## Idea (General outline)

Players can use in-game currency to buy ad space in the chat. The purchased ad (a text written by the player) is then automatically broadcast to all players on the server every few minutes.

## Rough Flow

1. Player buys an ad (text + payment via Vault/economy plugin).
2. The plugin sends the ad in a fixed interval (e.g. every 10 minutes) to all players in chat.
3. After some time or number of broadcasts, the ad expires.

