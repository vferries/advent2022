package advent.sponsors

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
            val toVisit = mutableListOf(currentNode to 0)
            while (toVisit.isNotEmpty()) {
                val (node, steps) = toVisit.removeAt(0)
                if (node.children.isNotEmpty()) {
                    totalSteps += steps
                    node.children.clear()
                    if (node.leftNode == null && node.rightNode == null) {
                        //TODO supprimer noeud et potentiellement ses parents
                    }
                    currentNode = node
                    break
                }
                //sinon on empile dans la liste ses voisins (devant ou derrière fonction des cas)
            }
        }
        return 0
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

data class PathNode(val path: String, val parent: PathNode?, var leftNode: PathNode? = null, var rightNode: PathNode? = null, val children: MutableList<String> = mutableListOf()) {
    fun allNodesWithStops(stops: Int = 0): List<Pair<PathNode, Int>> {
        val nextStops = if (leftNode == null || rightNode == null) stops else stops + 1
        val leftNodes = leftNode?.allNodesWithStops(nextStops) ?: listOf()
        val rightNodes = rightNode?.allNodesWithStops(nextStops) ?: listOf()
        return leftNodes + rightNodes + (this to stops)
    }

    override fun toString(): String {
        return "Node $children"
    }

    fun allNodes(): List<PathNode> {
        val leftNodes = leftNode?.allNodes() ?: listOf()
        val rightNodes = rightNode?.allNodes() ?: listOf()
        return leftNodes + rightNodes + this
    }
}
