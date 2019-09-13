/*
 * Copyright (c) 2019, Matsyir <https://github.com/matsyir>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.lmsperformancetracker;

import java.time.Instant;
import lombok.Getter;

// Basic class to hold information about PvP fight performances. A "successful" attack
// is dealt by not attacking with the style of the opponent's overhead. For example,
// attacking with range or melee against someone using protect from magic is a successful
// attack. Using melee against someone using protect from magic is not successful.
@Getter
public class FightPerformance
{
	private String playerName;
	private int playerAttackCount;
	private int playerSuccessCount;
	private double playerSuccessRate; // success rate in percentage
	private boolean playerDied;

	private String opponentName;
	private int opponentAttackCount;
	private int opponentSuccessCount;
	private double opponentSuccessRate; // success rate in percentage
	private boolean opponentDied;

	private Instant timeFightEnded; // date/time when the fight ends.

	// get a testing instance, used for debugging purposes.
	static FightPerformance getTestInstance(boolean random)
	{
		if (random)
		{
			int attacks = (int) (Math.random() * 35.0 + 20.0);
			int successful = (int) (attacks / 3.0 + ((Math.random() * (attacks / 2))));
			int oAttacks = (int) (Math.random() * 35.0 + 20.0);
			int oSuccessful = (int) (oAttacks / 3.0 + ((Math.random() * (oAttacks / 2))));

			return new FightPerformance(
				"qwerty" + (int) (Math.random() * 99.0),
				"asdf" + (int) (Math.random() * 99.0),
				attacks,
				successful,
				oAttacks,
				oSuccessful
			);
		}
		else
		{
			return new FightPerformance("qwerty123", "0123456789@#", 55, 32, 57, 43);
		}
	}

	// proper constructor which initializes a fight using only Player names, starting stats at 0.
	FightPerformance(String playerName, String opponentName)
	{
		this.playerName = playerName;
		this.opponentName = opponentName;
		playerAttackCount = 0;
		playerSuccessCount = 0;
		playerSuccessRate = 0;
		playerDied = false;
		opponentAttackCount = 0;
		opponentSuccessCount = 0;
		opponentSuccessRate = 0;
		opponentDied = false;
	}

	// used in getTestInstance, used for debugging purposes.
	private FightPerformance(String playerName, String opponentName, int playerAttackCount, int playerSuccessCount, int opponentAttackCount, int opponentSuccessCount)
	{
		this.playerName = playerName;
		this.opponentName = opponentName;
		this.playerAttackCount = playerAttackCount;
		this.playerSuccessCount = playerSuccessCount;
		this.playerSuccessRate = (double) this.playerSuccessCount / (double) this.playerAttackCount * 100.0;
		this.playerDied = false;
		this.opponentAttackCount = opponentAttackCount;
		this.opponentSuccessCount = opponentSuccessCount;
		this.opponentSuccessRate = (double) this.opponentSuccessCount / (double) this.opponentAttackCount * 100.0;
		this.opponentDied = false;
		this.timeFightEnded = Instant.now();
	}

	public void addAttack(String playerName, boolean success)
	{
		if (playerName.equals(this.playerName))
		{
			playerAttackCount++;
			if (success)
			{
				playerSuccessCount++;
			}
			playerSuccessRate = (double) playerSuccessCount / (double) playerAttackCount * 100.0;
		}
		else if (playerName.equals(opponentName))
		{
			opponentAttackCount++;
			if (success)
			{
				opponentSuccessCount++;
			}
			opponentSuccessRate = (double) opponentSuccessCount / (double) opponentAttackCount * 100.0;
		}
	}

	public void playerDied()
	{
		playerDied = true;
	}

	public void opponentDied()
	{
		opponentDied = true;
	}

	public void fightEnded()
	{
		timeFightEnded = Instant.now();
	}

	// Return a simple string to display the current player's success rate.
	// ex. "42/59 (71%)". The name is not included as it will be in a separate view.
	// Would round to 1 decimal space, but the overlay size is restrictive.
	public String getPlayerStatsString()
	{
		// The success rate is the percentage of successful attacks.
		return playerSuccessCount + "/" + playerAttackCount + " (" + Math.round(playerSuccessRate) + "%)";
	}

	// Return a simple string to display the current opponent's success rate.
	public String getOpponentStatsString()
	{
		// The success rate is the percentage of successful attacks.
		return opponentSuccessCount + "/" + opponentAttackCount + " (" + Math.round(opponentSuccessRate) + "%)";
	}

	// returns true if player success rate > opponent success rate.
	// could be "wrong" in some cases, if someone is eating a lot and not actually attacking much,
	// they could have a higher success rate than the person clearly winning. Although it is hard to
	// judge by comparing attack counts since someone using rune knives/msb against ballista/ags
	// is not uncommon, which could cause a situation opposite of the one described above.
	public boolean playerWinning()
	{
		return playerSuccessRate > opponentSuccessRate;
	}

	// returns true if opponent success rate > player success rate.
	public boolean opponentWinning()
	{
		return opponentSuccessRate > playerSuccessRate;
	}

	public void addAttacks(int success, int total)
	{
		playerSuccessCount += success;
		playerAttackCount += total;
		playerSuccessRate = (double) playerSuccessCount / (double) playerAttackCount * 100.0;
	}

	public boolean fightStarted()
	{
		return playerAttackCount > 0;
	}
}
