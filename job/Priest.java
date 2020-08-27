package job;

import java.util.Random;

// プレイヤー：僧侶
//最初のターンのみパライズかポイズンを使用
//HPが50以上減っていたらヒール、減っていなければ通常攻撃。
public class Priest extends Player {

	public Priest(String name)
	{
		super(name);
	}


	 //名前(name)からキャラクターに必要なパラメータを生成する

	@Override
	protected void MakeCharacter()
	{
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(0, 120)+80;   //80~200
		this.str = GetNumber(1, 60)+10;      //10~70
		this.def = GetNumber(2, 60)+10;          //10~70
		this.luck = GetNumber(3,99)+1;       //1~100
		this.agi = GetNumber(4,40)+20;        //20~60
		this.mp = GetNumber(5,30)+20;      //20~50
		this.type = "僧侶";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender,int turnNumber)
	{
		Random r = new Random();
        boolean parise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	parise = true;
        }

        ////麻痺判定 麻痺しているとき20%の確率で攻撃に失敗
        if(parise == true){
        	System.out.println(GetName()+"は痺れてうまく攻撃できなかった");
        }else{
        	int rndmagic;
    		//最初のターンのみパライズかポイズンを使用
    		//攻撃対象が味方だった場合ヒールを使用
    		if(turnNumber == 1){
                 rndmagic = r.nextInt(2);
                 switch(rndmagic){
                 case 0:this.Parise(defender);
                        break;
                 case 1:this.Poison(defender);
                        break;
                 }
    		}else if(defender.GetTEAM() == this.GetTEAM()){
                 this.Heal(defender);
            }
    		else
    		{
    			//通常攻撃
    			System.out.print(GetName() + "の攻撃！");
    	        int damage = CalcDamage(defender);

    	        // 求めたダメージを対象プレイヤーに与える
    	        System.out.print("\r\n"+defender.GetName() + "に" + damage + "のダメージ！       "+"HP:"+defender.GetHP()+" → ");
                defender.Damage(damage);
                System.out.println(defender.GetHP());

    		}
        }


        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}

    public void Heal(Player defender)
    {
	     System.out.print(GetName() + "のヒール！             ");
	     System.out.println(GetName() + "は50回復！");
	     defender.hp = defender.hp+50;         //初期HPから50以上ダメージを受けていた場合
	     this.mp = this.mp-20;         //ヒール使用 HP50回復、消費mp20
    }

    public void Parise(Player defender)
    {
	     defender.abnormality="parise";
	     System.out.println(GetName() + "のパライズ！");
	     System.out.println(defender.GetName() + "は麻痺した！");
    }

    public void Poison(Player defender)
    {
	     defender.abnormality="poison";
	     System.out.println(GetName() + "のポイズン！");
	     System.out.println(defender.GetName() + "は毒状態になった！");
    }

}