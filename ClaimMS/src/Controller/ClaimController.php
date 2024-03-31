<?php

namespace App\Controller;

use Eureka\EurekaClient;
use Eureka\Exceptions\RegisterFailureException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class ClaimController extends AbstractController
{
    /**
     * @throws RegisterFailureException
     */
    #[Route('/claim', name: 'book', methods: ['GET'])]
    public function index(): JsonResponse
    {
        $client = new EurekaClient([
            'eurekaDefaultUrl' => 'http://eureka-server:8761/eureka',
            'hostName' => 'www_symfony',
            'appName' => 'ClaimMS',
            'ip' => '127.0.0.1',
            'port' => ['8000', true]
        ]);
        $client->register();
        return $this->json([
            'message' => 'Welcome to your new controller!',
            'path' => 'src/Controller/ClaimController.php',
        ]);
    }
}
