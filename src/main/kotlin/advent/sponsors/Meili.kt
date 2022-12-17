package advent.sponsors

import java.util.*

object Meili {
    fun part1(lines: List<String>): String {
        val rootNode = constructTree(lines)

        val nodes = rootNode.allNodesWithStops()
            .filter { (node, _) -> node.children.isNotEmpty() }
        val minStops = nodes.minOf(Pair<PathNode, Int>::second)
        val nodesWithMinStops = nodes.filter { it.second == minStops }.map { it.first }
        return nodesWithMinStops.minBy { it.path }.children[0]
    }

    fun part2(lines: List<String>): Int {
        val rootNode = constructTree(lines)
        var currentNode = rootNode
        var totalSteps = 0
        while (rootNode.allNodes().any { it.children.isNotEmpty() }) {
            val toVisit =
                PriorityQueue(compareBy<Pair<PathNode, Int>> { it.second }.then(compareBy { it.first.path }))
            toVisit.add(currentNode to 0)
            val visited = mutableSetOf<PathNode>()
            while (toVisit.isNotEmpty()) {
                val (node, steps) = toVisit.remove()
                if (visited.contains(node)) {
                    continue
                }
                visited.add(node)
                if (node.children.isNotEmpty()) {
                    totalSteps += steps
                    println("Delivered presents to ${node.children} in $totalSteps steps")
                    node.children.clear()
                    currentNode = node
                    break
                } else {
                    toVisit.addAll(node.directNeighbors(steps))
                }
            }
        }
        return totalSteps
    }

    private fun constructTree(lines: List<String>): PathNode {
        val rootNode = PathNode("", null)
        for (line in lines) {
            val (name, path) = line.split(" - ")
            var currentNode = rootNode
            for (c in path) {
                currentNode = when (c) {
                    'L' -> {
                        val leftNode = currentNode.leftNode
                        if (leftNode == null) {
                            val newNode = PathNode(currentNode.path + c, currentNode)
                            currentNode.leftNode = newNode
                            newNode
                        } else {
                            leftNode
                        }
                    }

                    'R' -> {
                        val rightNode = currentNode.rightNode
                        if (rightNode == null) {
                            val newNode = PathNode(currentNode.path + c, currentNode)
                            currentNode.rightNode = newNode
                            newNode
                        } else {
                            rightNode
                        }
                    }

                    else -> throw UnsupportedOperationException("Unknown direction $c")
                }
            }
            currentNode.children.add(name)
        }
        return rootNode
    }
}

data class PathNode(
    val path: String,
    val parent: PathNode?,
    var leftNode: PathNode? = null,
    var rightNode: PathNode? = null,
    val children: MutableList<String> = mutableListOf()
) {
    fun allNodesWithStops(stops: Int = 0): List<Pair<PathNode, Int>> {
        val nextStops = if (leftNode == null || rightNode == null) stops else stops + 1
        val leftNodes = leftNode?.allNodesWithStops(nextStops) ?: listOf()
        val rightNodes = rightNode?.allNodesWithStops(nextStops) ?: listOf()
        return leftNodes + rightNodes + (this to stops)
    }

    override fun toString(): String {
        return "Node $path"
    }

    fun allNodes(): List<PathNode> {
        val leftNodes = leftNode?.allNodes() ?: listOf()
        val rightNodes = rightNode?.allNodes() ?: listOf()
        return leftNodes + rightNodes + this
    }

    fun directNeighbors(steps: Int): List<Pair<PathNode, Int>> {
        val neighbors = mutableListOf<Pair<PathNode, Int>>()
        if (this.parent != null) {
            val delta = if (this.parent.leftNode == null || this.parent.rightNode == null) 0 else 1
            neighbors.add(this.parent to steps + delta)
        }
        val delta = if (leftNode == null || rightNode == null) 0 else 1
        val leftNode = this.leftNode
        if (leftNode != null) {
            neighbors.add(leftNode to steps + delta)
        }
        val rightNode = this.rightNode
        if (rightNode != null) {
            neighbors.add(rightNode to steps + delta)
        }
        return neighbors
    }

    override fun hashCode(): Int {
        return path.hashCode()
    }
}
