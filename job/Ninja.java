package job;

// プレイヤー：忍者
//麻痺の影響をうけない
//25%の確率で攻撃をかわす
//1ターン目のみ攻撃が必ずクリティカル攻撃になる
//戦士に攻撃するとき戦士のDEFを半分にする
public class Ninja extends Player {

	// =======================
	// コンストラクタ
	// =======================
	public Ninja(String name)
	{
		super(name);
	}


	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter()
	{
		// 忍者のパラメータを名前から生成する
		this.hp = GetNumber(0, 100)+100;   //100~200
		this.str = GetNumber(1, 50)+20;      //20~70
		this.def = GetNumber(2, 70)+20;          //20~70
		this.luck = GetNumber(3,99)+1;       //1~100
		this.agi = GetNumber(4,40)+40;        //40~80
		this.type = "忍者";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender,int turnNumber)
	{
        // 与えるダメージを求める
		//最初のターンのみ相手の防御無視の攻撃をする
		//戦士に攻撃したときのみ相手のDEFを半分にしてダメージ計算する
        System.out.println(GetName() + "の攻撃！");
        int damage;
        if(turnNumber == 1){
        	damage = GetSTR();
        }else if(defender.GetTYPE() == "戦士"){
        	int stockDEF = defender.GetDEF();
        	defender.SetDEF(defender.GetDEF()/2);
            damage = CalcDamage(defender);
            defender.SetDEF(stockDEF);
        }else{
        	damage = CalcDamage(defender);
        }


        // 求めたダメージを対象プレイヤーに与える
        System.out.print(defender.GetName() + "に" + damage + "のダメージ！       "+"HP:"+defender.GetHP()+" → ");
        defender.Damage(damage);
        System.out.println(defender.GetHP());

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");

        }
	}

}
