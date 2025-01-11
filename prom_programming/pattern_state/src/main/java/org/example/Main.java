package org.example;

// Интерфейс состояния. методы принимают объект, позволяя состояниям изменять состояние контекста
interface PlayerState {
    void play(MediaPlayerContext context);
    void pause(MediaPlayerContext context);
    void stop(MediaPlayerContext context);
}

// Конкретные состояния
// класс реализует методы, когда плеер уже воспроизводит
class PlayingState implements PlayerState {
    @Override
    public void play(MediaPlayerContext context) {
        System.out.println("Медиаплеер уже воспроизводит.");
    }

    @Override
    public void pause(MediaPlayerContext context) {
        System.out.println("Пауза.");
        context.setState(new PausedState());
    }

    @Override
    public void stop(MediaPlayerContext context) {
        System.out.println("Остановка воспроизведения.");
        context.setState(new StoppedState());
    }
}

// класс реализует методы, когда плеер на паузе
class PausedState implements PlayerState {
    @Override
    public void play(MediaPlayerContext context) {
        System.out.println("Продолжение воспроизведения.");
        context.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayerContext context) {
        System.out.println("Медиаплеер уже на паузе.");
    }

    @Override
    public void stop(MediaPlayerContext context) {
        System.out.println("Остановка воспроизведения.");
        context.setState(new StoppedState());
    }
}

// класс реализует методы, когда плеер остановлен
class StoppedState implements PlayerState {
    @Override
    public void play(MediaPlayerContext context) {
        System.out.println("Начало воспроизведения.");
        context.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayerContext context) {
        System.out.println("Невозможно поставить на паузу, медиаплеер остановлен.");
    }

    @Override
    public void stop(MediaPlayerContext context) {
        System.out.println("Медиаплеер уже остановлен.");
    }
}

// Контекст - хранит текущее состояние плеера
class MediaPlayerContext {
    private PlayerState state;

    public MediaPlayerContext() {
        state = new StoppedState(); // Изначально медиаплеер остановлен
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }
}

// Главный класс
class MediaPlayer {
    public static void main(String[] args) {
        MediaPlayerContext player = new MediaPlayerContext();

        player.play();  // Начало воспроизведения
        player.pause(); // Пауза
        player.play();  // Продолжение воспроизведения
        player.stop();  // Остановка воспроизведения
        player.play();
        player.stop();  // Попытка остановить уже остановленный медиаплеер
    }
}