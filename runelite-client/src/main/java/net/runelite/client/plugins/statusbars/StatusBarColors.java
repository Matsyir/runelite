package net.runelite.client.plugins.statusbars;

import java.awt.Color;
import net.runelite.client.util.ColorUtil;

public class StatusBarColors
{

	static final Color PRAYER = new Color(50, 200, 200, 175);
	static final Color PRAYER_TICK = getTickVariation(PRAYER);

	static final Color ACTIVE_PRAYER = new Color(57, 255, 186, 225);
	static final Color ACTIVE_PRAYER_TICK = getTickVariation(ACTIVE_PRAYER);

	static final Color HEALTH = new Color(225, 35, 0, 125);
	static final Color HEALTH_TICK = getTickVariation(HEALTH);

	static final Color POISONED = new Color(0, 145, 0, 150);
	static final Color POISONED_TICK = getTickVariation(POISONED);

	static final Color VENOMED = new Color(0, 65, 0, 150);
	static final Color VENOMED_TICK = getTickVariation(VENOMED);

	static final Color HEAL = new Color(255, 112, 6, 150);
	static final Color HEAL_TICK = getTickVariation(HEAL);

	static final Color PRAYER_HEAL = new Color(57, 255, 186, 75);
	static final Color PRAYER_HEAL_TICK = getTickVariation(PRAYER_HEAL);

	static final Color ENERGY_HEAL = new Color (199,  118, 0, 218);
	static final Color ENERGY_HEAL_TICK = getTickVariation(ENERGY_HEAL);

	static final Color RUN_STAMINA = new Color(160, 124, 72, 255);
	static final Color RUN_STAMINA_TICK = getTickVariation(RUN_STAMINA);

	static final Color SPECIAL_ATTACK = new Color(3, 153, 0, 195);
	static final Color SPECIAL_ATTACK_TICK = getTickVariation(SPECIAL_ATTACK);

	static final Color ENERGY = new Color(199, 174, 0, 220);
	static final Color ENERGY_TICK = getTickVariation(ENERGY);

	static final Color DISEASE = new Color(255, 193, 75, 181);
	static final Color DISEASE_TICK = getTickVariation(DISEASE);

	static final Color PARASITE = new Color(196, 62, 109, 181);
	static final Color PARASITE_TICK = getTickVariation(PARASITE);

	private static Color getTickVariation(Color c)
	{
		return ColorUtil.colorWithAlpha(ColorUtil.darken(c, 0.15), (int)(c.getAlpha() * 0.7));
	}

	public static Color getPrayer(boolean isTick)
	{
		return isTick ? PRAYER_TICK : PRAYER;
	}
	public static Color getActivePrayer(boolean isTick)
	{
		return isTick ? ACTIVE_PRAYER_TICK : ACTIVE_PRAYER;
	}
	public static Color getHealth(boolean isTick)
	{
		return isTick ? HEALTH_TICK : HEALTH;
	}
	public static Color getPoisoned(boolean isTick)
	{
		return isTick ? POISONED_TICK : POISONED;
	}
	public static Color getVenomed(boolean isTick)
	{
		return isTick ? VENOMED_TICK : VENOMED;
	}
	public static Color getHeal(boolean isTick)
	{
		return isTick ? HEAL_TICK : HEAL;
	}
	public static Color getPrayerHeal(boolean isTick)
	{
		return isTick ? PRAYER_HEAL_TICK : PRAYER_HEAL;
	}
	public static Color getEnergyHeal(boolean isTick)
	{
		return isTick ? ENERGY_HEAL_TICK : ENERGY_HEAL;
	}
	public static Color getRunStamina(boolean isTick)
	{
		return isTick ? RUN_STAMINA_TICK : RUN_STAMINA;
	}
	public static Color getSpecialAttack(boolean isTick)
	{
		return isTick ? SPECIAL_ATTACK_TICK : SPECIAL_ATTACK;
	}
	public static Color getEnergy(boolean isTick)
	{
		return isTick ? ENERGY_TICK : ENERGY;
	}
	public static Color getDisease(boolean isTick)
	{
		return isTick ? DISEASE_TICK : DISEASE;
	}
	public static Color getParasite(boolean isTick)
	{
		return isTick ? PARASITE_TICK : PARASITE;
	}
}
