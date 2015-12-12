function c101001.initialize_effect(card)
	println("Call Initialize")
	local e = Effect:createEffect()
	e:setType(EFFECT_TYPE_TRIGGER)
	e:setRange(LOCATION_HAND)
	e:setCode(EVENT_TO_HAND)
	e:setCondition(c101001.con)
	e:setOperation(c101001.op)
	card:registerEffect(e);
end

function c101001.op(game, player, op_player, card, effect)
	println("Call Operation")
	local e = Effect:createEffect()
	e:setType(EFFECT_TYPE_SINGLE)
	e:setCode(EFFECT_UPDATE_ATTACK)
	e:setValue(1000)
	e:setReset(RESET_PHASE+PHASE_END)
	card:registerEffect(e)
	return true
end

function c101001.con(game, player, op_player, card, effect)
	println("Call Condition")
	if card:getPreviousLocation() == LOCATION_DECK then
		if game:chooseMessage("許可","未許可") == 0 then
			return true
		end
	end
	return false
end

function c101001.cost(game, player, op_player, card, effect, chk)
	if chk==0 then
		player:getResource()
	end
	
	local sg=Duel.SelectReleaseGroup(tp,c69846323.cfilter,1,1,e:GetHandler())
	Duel.Release(sg,REASON_COST)
end

function c101001.filter(card)
	if chk==0 then
		player:getResource()
	end
	
	local sg=Duel.SelectReleaseGroup(tp,c69846323.cfilter,1,1,e:GetHandler())
	Duel.Release(sg,REASON_COST)
end