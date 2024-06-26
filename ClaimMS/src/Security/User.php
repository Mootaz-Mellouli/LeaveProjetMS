<?php

namespace App\Security;

use Lexik\Bundle\JWTAuthenticationBundle\Security\User\JWTUser;

final class User extends JWTUser
{
    const ROLE_USER = 'ROLE_USER';
    const ROLE_CLIENT = 'ROLE_CLIENT';

    /** @var string */
    private $email;

    public function __construct(string $username, ?string $email, array $roles = [])
    {
        parent::__construct($username, $roles);
        $this->email = $email;
    }

    /**
     * {@inheritdoc}
     */
    public static function createFromPayload($username, array $payload)
    {
        /* Ajoute le role user si user identifié
          sinon c'est une génération du token avec un client ID / secret*/
        if (array_key_exists('clientId', $payload)) {
            $roles = [self::ROLE_CLIENT];
        } else {
            $roles = [self::ROLE_USER];
        }
        /* On récupère les roles du client qui porte le nom du micro-service */
        /* !! CHANGE firstservice BY YOUR !! */
        if (isset($payload['resource_access']) && isset($payload['resource_access']['firstservice'])) {
            $roles = array_merge($roles, $payload['resource_access']['firstservice']['roles']);
        }

        return new static($username, array_key_exists('email', $payload) ? $payload['email'] : null, array_unique($roles));
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

}