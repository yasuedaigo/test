package game;
import java.util.ArrayList;

import job.Player;
public class Party {
// =======================
// フィールド変数
// =======================
private ArrayList<Player> members;
// =======================
// コンストラクタ
// =======================
Party() {
 members = new ArrayList<Player>();
}
// =======================
// Getter / Setter
// =======================
/**
* パーティーメンバーを ArrayList で取得する
*/
public ArrayList<Player> GetMembers() {
return members;
}

public Player GetPlayer(int i) {
	return members.get(i);
}

/**
* パーティーにプレイヤーを追加する
* @param player : 追加するプレイヤー
*/
public void AppendPlayer(Player player) {
     members.add(player);
}
/**
* パーティーからプレイヤーを離脱させる
* @param player : 離脱させるプレイヤー
*/
public void RemovePlayer(Player player) {
}
}
