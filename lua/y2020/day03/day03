local input = fs.open("input.txt", "rb")
local map = {}

function parseInput()
  local line = 1
  local i = 1
  map.width = 1
  while true do
    local c = input.read()
    if c ~= nil then
      if c ~= 10 then           --ASCII code for \n
        if map[i]== nil then map[i] = {} end
        if map.width < i then map.width = i end
        map[i][line] = (c == 35) --ASCII code for #
        i = i + 1
      else 
        line = line + 1
        i = 1
      end
    else
      break
    end
  end
  map.height = line
  input.close()
end

function sumTrees(stepX, stepY)
  local sum = 0
  for i=1,map.height do
    local x = math.fmod(i * stepX, map.width) + 1
    local y = i * stepY + 1
    if y < map.height then
      if map[x][y] then
        sum = sum + 1 
      end
    end
  end
  return sum
end

parseInput()
print("\n--- Day 3: Toboggan Trajectory ---\n")
print("Your puzzle answer is " .. sumTrees(3, 1))
print("\n--- Part Two ---\n")
print("Your puzzle answer is " .. sumTrees(1, 1) * sumTrees(3, 1) * sumTrees(5, 1) * sumTrees(7, 1) * sumTrees(1, 2))
print("")
