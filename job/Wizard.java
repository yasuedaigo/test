package job;

import java.util.Random;

// プレイヤー：魔法使い
//mpに応じて場合使える魔法をランダムに使用。使える魔法がない場合通常攻撃
public class Wizard extends Player {
	Random r = new Random();

	public Wizard(String name)
	{
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter()
	{
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(0, 100)+50;   //50~150
		this.str = GetNumber(1, 50);      //1~50
		this.def = GetNumber(2, 50);          //1~50
		this.luck = GetNumber(3,100);       //1~100
		this.agi = GetNumber(4,40)+20;        //20~60
		this.mp = GetNumber(5,50)+30;      //30~80
		this.type = "魔法使い";
		this.abnormality ="";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender,int turnNumber)
	{
		System.out.print(GetName() + "の攻撃！");
        //麻痺判定 麻痺しているとき20%の確率で攻撃に失敗
		Random r = new Random();
        boolean parise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	parise = true;
        }

        if(parise == true){
        	System.out.println("   "+GetName()+"は痺れてうまく攻撃できなかった");
        }else{
        	if(this.mp >= 20){
    			int rndmagic = r.nextInt(2);            //使える魔法が2つあるときにランダムにどちらかを使えるようにする変数rndmagic
    			switch(rndmagic){                       //mpが20以上のときファイアかサンダーをランダムで使用
    			   case 0:this.Fire(defender);
    			          break;
    			   case 1:this.Thunder(defender);
    			          break;
    			}
    		    }else if(this.mp < 20||this.mp >= 10){
    				 this.Fire(defender);               //mpが10以上20以下のときファイアを使用
    			}else{
    				//通常攻撃
    				// 与えるダメージを求める
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

	public void Thunder(Player defender){
		//サンダー
        System.out.println("   "+GetName() + "のサンダー！");
        int damage = r.nextInt(21)+10;         //10~30の防御無視ダメージ
        this.mp = this.mp-20;          //mp消費-20

        System.out.print(defender.GetName() + "に" + damage + "のダメージ！       "+"HP:"+defender.GetHP()+" → ");
        defender.Damage(damage);
        System.out.println(defender.GetHP());
	}


	public void Fire(Player defender){
		//ファイア
        System.out.println("   "+GetName() + "のファイア！");
        int damage = r.nextInt(21)+10;         //10~30の防御無視ダメージ
        this.mp = this.mp-10;          //mp消費-10

        System.out.print(defender.GetName() + "に" + damage + "のダメージ！       "+"HP:"+defender.GetHP()+" → ");
        defender.Damage(damage);
        System.out.println(defender.GetHP());
	}

}
