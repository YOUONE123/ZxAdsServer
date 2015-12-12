Card = {}

i = 0

function Card.add(a, b)
	b:println("Lua Java Method Call Test")
	i = i + 1
	return i
end

function Card.test2(a, b)
	b:println("Lua Java Method Call Test2")
end

function Card.test3(a, b)
	b:println("Lua Java Method Call Test3")
	local a = Effect:createEffect();
end