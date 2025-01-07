package oit.is.quizrock.quiz6.service;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
  private int playerCount = 0;

  public synchronized void playerJoined() {
    playerCount++;
  }

  public synchronized boolean isReadyToStart() {
    return playerCount >= 2;
  }

  public synchronized void resetPlayers() {
    playerCount = 0;
  }
}
