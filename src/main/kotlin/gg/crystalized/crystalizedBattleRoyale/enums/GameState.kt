package gg.crystalized.crystalizedBattleRoyale.enums

enum class GameState {
    WAITING, //waiting for minimum players
    STARTING, //when the minimum players amount required was already reached and the game is going to start
    WARMUP, //warmup phase (pvp off)
    RUNNING1, //first phase (pvp on)
    RUNNING2, //second phase (pvp on)
    ENDING //last players remaining (maybe do some fancy stuff idk?)
}