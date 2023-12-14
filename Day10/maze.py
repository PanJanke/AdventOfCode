def load_maze(path):
    lines = open(path).read().splitlines()
    transposed_grid = [list(line) for line in lines]
    grid = [[transposed_grid[y][x] for y in range(len(transposed_grid))] for x in range(len(transposed_grid[0]))]
    return grid


def find_char(grid, target_char):
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == target_char:
                return i, j


def get_neighbors(grid, row, column):
    neighbors = []
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    for dr, dc in directions:
        new_row, new_column = row + dr, column + dc

        if 0 <= new_row < len(grid) and 0 <= new_column < len(grid[0]):
            neighbor_value = grid[new_row][new_column]
            neighbors.append(neighbor_value)
        else:
            neighbors.append(None)

    return neighbors


def check_neighbors(neighbors, x, y):
    North = neighbors[2]
    South = neighbors[3]
    West = neighbors[0]
    East = neighbors[1]

    list_of_cells = []

    if (North == '|' or North == '7' or North == 'F'):
        list_of_cells.append((x, y - 1))
    if (South == '|' or South == 'L' or South == 'J'):
        list_of_cells.append((x, y + 1))
    if (East == '-' or East == '7' or East == 'J'):
        list_of_cells.append((x + 1, y))
    if (West == '-' or West == 'L' or West == 'F'):
        list_of_cells.append((x - 1, y))

    return list_of_cells


def get_to_next_cell(prev_cell, curr_cel, char):
    #print("KURWA JEGO MAC BYLEM NA:", prev_cell, "Stoje na:", curr_cel, "numerek:", char)
    if char == '|':
        diff = curr_cel[1] - prev_cell[1]
        return curr_cel[0], curr_cel[1] + diff
    elif char == '-':
        diff = curr_cel[0] - prev_cell[0]
        return curr_cel[0] + diff, curr_cel[1]
    elif char == 'J':
        if prev_cell[1] < curr_cel[1]:
            return curr_cel[0] - 1, curr_cel[1]
        else:
            return curr_cel[0], curr_cel[1] - 1
    elif char == '7':
        if prev_cell[1] > curr_cel[1]:
            return curr_cel[0] - 1, curr_cel[1]
        else:
            return curr_cel[0], curr_cel[1] + 1
    elif char == 'L':
        if prev_cell[0] != curr_cel[0]:
            return curr_cel[0], curr_cel[1] - 1
        else:
            return curr_cel[0] + 1, curr_cel[1]
    elif char == 'F':
        if prev_cell[0] != curr_cel[0]:
            return curr_cel[0], curr_cel[1] + 1
        else:
            return curr_cel[0] + 1, curr_cel[1]
    else:
        print("SMTH WRONG ", prev_cell, curr_cel, char)
        return None


grid = load_maze("day10.txt")
target_character = 'S'
result = find_char(grid, target_character)
x, y = result
neighbors = get_neighbors(grid, x, y)
startPositions = check_neighbors(neighbors, x, y)



pos_a, pos_b = startPositions
prev_a, prev_b = [x, y], [x, y]
counter = 1
while pos_a != pos_b:
    tmp_a = pos_a
    tmp_b = pos_b
    pos_a = get_to_next_cell(prev_a, pos_a, grid[pos_a[0]][pos_a[1]])
    pos_b = get_to_next_cell(prev_b, pos_b, grid[pos_b[0]][pos_b[1]])
    prev_a = tmp_a
    prev_b = tmp_b
    counter += 1

print(counter)
